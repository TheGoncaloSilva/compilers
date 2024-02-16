/*
 *  \author ...
 */

#include "somm22.h"
#include "sim_module.h"

namespace somm22 
{

    namespace group
    {

// ================================================================================== //

        /*
         * \brief Init the module's internal data structure
         */
        void simInit()
        {
            soProbe(501, "%s()\n", __func__);
            //arrival = 0b001
			//postponed = 0b010
			//terminated = 0b100
            sim::currentSimMask = 0b101;
            sim::currentSimStep = 0;
            sim::currentSimTime = 0;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
